package nivel1.Ext1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public final class Serializer {

    public static void serializeObject(Serializable object, File outputFile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            out.writeObject(object);
        }

    }

    public static Object deserializeObject(File inputFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(inputFile))) {
            return in.readObject();
        }
    }


}
