package treebuddy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {

    private String filePath;
    /**
     * Creates a Storage object with the given file path.
     *
     * @param filePath Path to the data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {

        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            file.createNewFile();
            return tasks;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");

            Task t;

            if (type.equals("T")) {
                t = new ToDo(parts[2]);
            } else if (type.equals("D")) {
                t = new Deadline(parts[2], parts[3]);
            } else {
                t = new Event(parts[2], parts[3], parts[4]);
            }

            if (isDone) {
                t.markAsDone();
            }

            tasks.add(t);
        }

        br.close();
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

        for (Task t : tasks) {
            bw.write(t.toFileString());
            bw.newLine();
        }
        bw.close();
    }
}