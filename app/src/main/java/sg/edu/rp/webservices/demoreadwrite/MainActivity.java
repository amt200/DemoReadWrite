package sg.edu.rp.webservices.demoreadwrite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    Button btnWrite, btnRead;
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI handlers to be defined
        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
        tvContent = findViewById(R.id.tv);

        folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyFolder";

        File folder = new File(folderLocation);
        if (!folder.exists()){
            boolean result = folder.mkdir();
            if (result){
                Log.d("File Read/Write", "Folder created");
            }
        }
        Log.d("Folder location", folderLocation);


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for file writing
                File targetFile = new File(folderLocation, "data.txt");
                try {
                    FileWriter writer = new FileWriter(targetFile, true);
                    writer.write("Hello world");
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for file reading
                File targetFile = new File(folderLocation, "data.txt");
                if(targetFile.exists()){
                    String data = "";
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        String line = bufferedReader.readLine();
                        while (line != null){
                            data += line+"\n";
                            line = bufferedReader.readLine();
                        }
                        bufferedReader.close();
                        reader.close();
                        tvContent.setText(data);
                    }
                    catch (Exception e){
                        Toast.makeText(MainActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();
                        tvContent.setText(data);
                    }

                }
            }
        });
    }

}