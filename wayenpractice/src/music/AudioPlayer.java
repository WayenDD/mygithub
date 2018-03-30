package music;

import java.beans.Encoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
/***
 - 音乐播放器类
 */
public class AudioPlayer{
    Player player;
    File music;
    //构造方法  参数是一个.mp3音频文件
    public AudioPlayer(File file) {
        this.music = file;
    }
    //播放方法
    public void play() throws FileNotFoundException, JavaLayerException {

            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
            player = new Player(buffer);
            player.play();
    }
    
    public static void main(String[] args) throws JavaLayerException, IOException {
    	File file = new File("D:\\自定义sql\\把悲伤留给自己.mp3");
    	FileInputStream fis=new FileInputStream(file);
    	int b=fis.available();
    	Bitstream bt=new Bitstream(fis);
    	Header h = bt.readFrame();
    	int time = (int) h.total_ms(b);
    	int i = time/1000;
    	System.out.println(i/60 + ":" + i%60);
    	
		new AudioPlayer(new File("D:\\自定义sql\\把悲伤留给自己.mp3")).play();
		
	}
}
