package com.expocms.server.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {
	public static byte[] compress(byte[] data) throws Exception {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        GZIPOutputStream gos = new GZIPOutputStream(baos);  
        gos.write(data, 0, data.length);  
        gos.finish();  
        byte[] output = baos.toByteArray();  
        baos.flush();  
        baos.close();  
        return output;  
    }  
}
