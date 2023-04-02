import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NotesApp {
    static ArrayList<Note> notesList = new ArrayList<Note>();
    static Gson gson = new Gson();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean quit = false;

        while (!quit) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a note");
            System.out.println("2. Edit a note");
            System.out.println("3. Delete a note");
            System.out.println("4. View all notes");
            System.out.println("5. Quit");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    editNote();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    viewNotes();
                    break;
                case 5:
                    quit = true;
                    System.out.println("Exiting notes app...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    public static void addNote() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your note title:");
        String title = scanner.nextLine();
        System.out.println("Enter your note content:");
        String content = scanner.nextLine();
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        notesList.add(note);
        System.out.println("Note added successfully!");
    }

    public static void editNote() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the index of the note you want to edit:");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < notesList.size()) {
            System.out.println("Enter your new note title:");
            String newTitle = scanner.nextLine();
            System.out.println("Enter your new note content:");
            String newContent = scanner.nextLine();
            Note note = new Note();
            note.setTitle(newTitle);
            note.setContent(newContent);
            notesList.set(index, note);
            System.out.println("Note edited successfully!");
        } else {
            System.out.println("Invalid index, try again.");
        }
    }

    public static void deleteNote() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the index of the note you want to delete:");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < notesList.size()) {
            notesList.remove(index);
            System.out.println("Note deleted successfully!");
        } else {
            System.out.println("Invalid index, try again.");
        }
    }

    public static void viewNotes() {
        if (notesList.isEmpty()) {
            System.out.println("No notes to display.");
        } else {
            System.out.println("Here are your notes:");
            for (int i = 0; i < notesList.size(); i++) {
                System.out.println("Note " + (i + 1) + ":");
                System.out.println("Title: " + notesList.get(i).getTitle());
                System.out.println("Content: " + notesList.get(i).getContent());
                System.out.println("Date: " + notesList.get(i).getDate());
                System.out.println();
            }
        }
    }

    public static void loadNotesFromJson(String jsonString) {
        try {
            Note[] notes = gson.fromJson(jsonString, Note[].class);
            notesList.clear();
            for (Note note : notes) {
                notesList.add(note);
            }
            System.out.println("Notes loaded successfully!");
        } catch (JsonSyntaxException e) {
            System.out.println("Invalid JSON syntax, notes not loaded.");
        }
    }
}