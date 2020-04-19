package com.gmail.ssoch.healthcheck.dao.file;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

public class AppendDataToFile {
    private Context applicationContext;
    private String file_name;

    public AppendDataToFile(Context applicationContext, String file_name) {
        this.applicationContext = applicationContext;
        this.file_name = file_name;
    }

    public void append(String data) throws IOException {
        persistData(data, MODE_APPEND);
    }

    public void save(String data) throws IOException {
        persistData(data, MODE_PRIVATE);
    }

    private void persistData(String data, int persistMode) throws IOException {
        FileOutputStream fileOutputStream = applicationContext.openFileOutput(file_name, persistMode);
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
        writer.write(data);
        writer.close();
        fileOutputStream.close();
    }


}
