package persistence;

import com.google.gson.Gson;
import model.MasterTask;

import java.io.*;

// A writer that writes data to file
public class Writer {
    private FileWriter fileWriter;

    // EFFECTS: ;constructs a writer that will write data to a file
    public Writer(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing data!
    public void close() throws IOException {
        fileWriter.close();
    }

/*    public void toJSON(MasterTask task) {
        Gson gson = new Gson();
        String parseMasterTask = gson.toJson(task);
        System.out.println(parseMasterTask);
    }*/
}
