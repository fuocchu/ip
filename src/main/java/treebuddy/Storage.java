package treebuddy;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String filePath;

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
            String desc = parts[2];

            Task t = null;

            if (type.equals("T")) {
                t = new ToDo(desc);
            } else if (type.equals("D")) {
                t = new Deadline(desc, parts[3]);
            } else if (type.equals("E")) {
                t = new Event(desc, parts[3], parts[4]);
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

        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Task t : tasks) {

            String line = "";

            if (t instanceof ToDo) {
                line = "T | " + (t.isDone ? "1" : "0") + " | " + t.getDescription();
            }
            else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                line = "D | " + (t.isDone ? "1" : "0") + " | "
                        + t.getDescription() + " | " + d.getBy();
            }
            else if (t instanceof Event) {
                Event e = (Event) t;
                line = "E | " + (t.isDone ? "1" : "0") + " | "
                        + t.getDescription() + " | "
                        + e.getFrom() + " | " + e.getTo();
            }

            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }
}