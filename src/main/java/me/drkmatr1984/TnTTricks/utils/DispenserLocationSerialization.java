package me.drkmatr1984.TnTTricks.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class DispenserLocationSerialization {
    public static String toBase64(Set<DispenserLocation> setLocs) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            // Save every element in the list
            for (DispenserLocation loc : setLocs) {
                dataOutput.writeObject(loc);
            }           
            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save blocks.", e);
        }        
    }
    
    public static Set<DispenserLocation> fromBase64(String data, int size) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Set<DispenserLocation> setLocs = new HashSet<DispenserLocation>();
            // Read the serialized list
            for (int i = 0; i < size; i++) {
            	setLocs.add((DispenserLocation)dataInput.readObject());
            }
            dataInput.close();
            return setLocs;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
}