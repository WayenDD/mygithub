package client;

import io.netty.bootstrap.Bootstrap;  
import io.netty.channel.ChannelFuture;  
import io.netty.channel.ChannelInitializer;  
import io.netty.channel.ChannelOption;  
import io.netty.channel.EventLoopGroup;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioSocketChannel;  
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;  
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.io.IOException;  
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import config.IMConfig;
import config.IMMessage;
import encode.LuckDecoder;
import encode.LuckEncoder;  
  
public class Client implements Runnable,IMConfig {  
    public static int UID = -1;  
    public static int toID = -1;  
    private ClientHandler clientHandler = new ClientHandler();  
    
    public static void main(String[] args) throws IOException{  
        new Client().start();  
    }  
    
    public void start() throws IOException{  
        new Thread(this).start();  
        runServerCMD();  
    }  
    
    public void sendMsg(IMMessage msg) throws IOException {  
        clientHandler.sendMsg(msg);  
    }  
    
    /**启动客户端控制台*/  
    private void runServerCMD() throws IOException {  
        IMMessage message = new IMMessage(  
                APP_IM,  
                CLIENT_VERSION,  
                UID,  
                TYPE_MSG_TEXT,  
                toID,  
                MSG_EMPTY);  
        @SuppressWarnings("resource")  
        Scanner scanner = new Scanner(System.in);  
        do{  
            message.setMsg(scanner.nextLine());  
        }  
        while (clientHandler.sendMsg(message));  
    }  
    @Override  
    public void run() {  
        EventLoopGroup workerGroup = new NioEventLoopGroup();  
        try {  
            Bootstrap b = new Bootstrap();  
            b.group(workerGroup);  
            b.channel(NioSocketChannel.class);  
            b.option(ChannelOption.SO_KEEPALIVE, true);  
            b.handler(new ChannelInitializer<SocketChannel>() {  
                @Override  
                public void initChannel(SocketChannel ch) throws Exception {  
                	ch.pipeline().addLast("ping",new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
                    ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65536, 0, 2, 0, 2));  
                    ch.pipeline().addLast("msgpack decoder",new LuckDecoder());  
                    ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));  
                    ch.pipeline().addLast("msgpack encoder",new LuckEncoder());  
                    ch.pipeline().addLast(clientHandler);  
                }  
            });  
            ChannelFuture f = b.connect(SERVER_HOST, SERVER_PORT).sync();  
            f.channel().closeFuture().sync();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } finally {  
            workerGroup.shutdownGracefully();  
        }  
    }  
}  
