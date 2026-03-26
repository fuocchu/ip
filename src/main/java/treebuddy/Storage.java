package treebuddy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to a persistent file.
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

    /**
     * Loads tasks from the data file and returns them as an ArrayList.
     *
     * Creates the file if it does not exist. Each line is parsed into
     * a ToDo, Deadline, or Event task based on the stored type prefix.
     *
     * @return ArrayList of tasks loaded from the file
     * @throws IOException If a file I/O error occurs
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            file.createNewFile();
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
        }

        return tasks;
    }

    /**
     * Saves all tasks to the data file, overwriting any existing content.
     *
     * @param tasks The list of tasks to save
     * @throws IOException If a file I/O error occurs
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                bw.write(t.toFileString());
                bw.newLine();
            }
        }
    }
}
