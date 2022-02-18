import java.io.File;

public class LockFile {
    File f1, f2;

    LockFile(String n1, String n2) {
        f1 = new File(n1);
        f2 = new File(n1);

    }

    void blockcreate() {

            try {
                System.out.println("file in creazione");
                f2.createNewFile();
                System.out.println("file creato");
            } catch (Exception e) {
                System.out.println("error 1.0");
                e.printStackTrace();

            }

    }

            //mostra il primo titolo
    void blockremove(){
            try {
                System.out.println("file in rimozione");
                f2.delete();
                System.out.println("file rimosso");
            } catch (Exception e) {
                System.out.println("error 2.0");
                e.printStackTrace();
            }
        }
}
