package com.syhan.edu.mongodb.file;

import java.io.File;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

public class GridFSUploadTest {
	public static void main(String[] args) throws Exception {
		MongoClient client = new MongoClient();
		DB db = client.getDB("gridtest");
		
		File file = new File("/tmp/nosqldbs.pdf");
		GridFS gridfs = new GridFS(db, "pdf_files");
		GridFSInputFile gfsFile = gridfs.createFile(file);
		gfsFile.setFilename("nosql-pdf-file");
		gfsFile.save();
		System.out.println(gfsFile.getId());
	}
}
