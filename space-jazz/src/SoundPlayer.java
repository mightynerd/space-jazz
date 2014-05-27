//
//	Project space-jazz
//	Licenced under the Genereal Public Licence v2
//
//	SoundPlayer.java
//	Plays *.wav files
//

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


public class SoundPlayer {
	
	private String filePath;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private DataLine.Info info;
	private Clip clip;
	
	public SoundPlayer(String path)
	{
		try
		{
			audioStream = AudioSystem.getAudioInputStream(new File("content" + File.separator + path));
			audioFormat = audioStream.getFormat();
			info = new DataLine.Info(Clip.class, audioFormat);
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(audioStream);
			
		} catch (Exception ex)
		{
			System.out.println("SoundPlayer error: " + ex.getMessage());
		}
	}
	
	public void Reset()
	{
		clip.stop();
		clip.setFramePosition(0);
	}
	
	public void Play()
	{
		clip.start();
	}
	
	public void Stop()
	{
		clip.stop();
	}
	
}
