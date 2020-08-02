package persistence;

import com.google.gson.Gson;
import model.MasterTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Writer {
    private PrintWriter printWriter;

    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(file, "UTF-8");
    }

    // MODIFIES: this
    // EFFECTS: writes saveable to file
    public void write(Saveable saveable) {
        saveable.save(printWriter);
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing data!
    public void close() {
        printWriter.close();
    }

/*    public void toJSON(MasterTask task) {
        Gson gson = new Gson();
        String parseMasterTask = gson.toJson(task);
        System.out.println(parseMasterTask);
    }*/
}
