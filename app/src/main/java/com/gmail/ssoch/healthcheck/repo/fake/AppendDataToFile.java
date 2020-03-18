package com.gmail.ssoch.healthcheck.repo.fake;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_APPEND;

public class AppendDataToFile {


    private Context applicationContext;
    private String file_name;
    private int modeAppend;

    public AppendDataToFile(Context applicationContext, String file_name, int modeAppend) {
        this.applicationContext = applicationContext;
        this.file_name = file_name;
        this.modeAppend = modeAppend;
    }

    public boolean append(String data) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = applicationContext.openFileOutput("hc_blood_pressure.txt", MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.write(data.toString());
            writer.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
