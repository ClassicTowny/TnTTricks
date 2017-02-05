package me.drkmatr1984.TnTTricks.serialization;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class SetSerialization {
    public static String toBase64(Set<DispenserLocation> dispenserLocations) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            // Save every element in the list
            for (Object loc : dispenserLocations) {
                dataOutput.writeObject(loc);
            }           
            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save DispenserLocations.", e);
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
    
    public static Set<DispenserLocation> listToSet(List<DispenserLocation> list){
		Set<DispenserLocation> setString = new HashSet<DispenserLocation>();
		for(DispenserLocation s : list){
			setString.add(s);
		}
		if(!(setString.isEmpty())){
			return setString;
		}
		return null;
	}
	
	public static List<DispenserLocation> setToList(Set<DispenserLocation> list){
		List<DispenserLocation> setString = new ArrayList<DispenserLocation>();
		for(DispenserLocation s : list){
			setString.add(s);
		}
		if(!(setString.isEmpty())){
			return setString;
		}
		return null;
	}
}