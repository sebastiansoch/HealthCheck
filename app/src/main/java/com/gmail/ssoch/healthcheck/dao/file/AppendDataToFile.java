package com.gmail.ssoch.healthcheck.dao.file;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_APPEND;

public class AppendDataToFile {
    private Context applicationContext;
    private String file_name;

    public AppendDataToFile(Context applicationContext, String file_name) {
        this.applicationContext = applicationContext;
        this.file_name = file_name;
    }

    public void append(String data) throws IOException {
        FileOutputStream fileOutputStream = applicationContext.openFileOutput(file_name, MODE_APPEND);
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
        writer.write(data);
        writer.close();
        fileOutputStream.close();
    }
}
