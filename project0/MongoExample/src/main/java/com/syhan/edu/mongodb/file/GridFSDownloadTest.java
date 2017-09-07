package com.syhan.edu.mongodb.file;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

public class GridFSDownloadTest {
	public static void main(String[] args) throws Exception {
		MongoClient client = new MongoClient();
		DB db = client.getDB("gridtest");
		GridFS gridfs = new GridFS(db, "pdf_files");
		GridFSDBFile gridFile = gridfs.findOne("nosql-pdf-file");
		gridFile.writeTo("/tmp/nosqldbs_new.pdf");
	}
}
