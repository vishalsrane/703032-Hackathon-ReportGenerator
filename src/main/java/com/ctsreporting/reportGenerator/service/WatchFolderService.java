package com.ctsreporting.reportGenerator.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

//code snippets were taken from https://www.baeldung.com/java-nio2-watchservice

@Service
public class WatchFolderService implements CommandLineRunner{
	
	static final String FOLDER_TO_MONITOR = "/home/vishal/projects/hackfse/folder-to-monitor"; //"C:\\hck-fse\\folder-to-monitor";
	
	@Autowired
	private WatchService watchService;
	
	@Override
	public void run(String... args) throws IOException, InterruptedException{
		Path path = Paths.get(FOLDER_TO_MONITOR);
		path.register(
		          watchService, 
		            StandardWatchEventKinds.ENTRY_CREATE, 
		                StandardWatchEventKinds.ENTRY_MODIFY);
		WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }
	}

}
