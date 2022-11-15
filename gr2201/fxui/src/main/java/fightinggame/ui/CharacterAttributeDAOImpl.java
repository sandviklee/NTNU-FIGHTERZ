package fightinggame.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fightinggame.characterjson.GameplayModule;

import fightinggame.game.PlayerProperties;

public class CharacterAttributeDAOImpl implements CharacterAttributeDAO{
	private String path;
	private ObjectMapper mapper = new ObjectMapper();

	public CharacterAttributeDAOImpl(){
		this.path = "gr2201/gr2201/fxui/src/main/resources/fightinggame/ui/";		
		mapper.registerModule(new GameplayModule());
	}

	@Override
	public ArrayList<PlayerProperties> getAllCharacters() {
		try {
			String fileJson = readFromFile(this.getPath());
			return this.deserializerPlayerProperties(fileJson);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound at " + this.getPath());
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return new ArrayList<PlayerProperties>();
	}

	@Override
	public PlayerProperties findCharacter(String characterName) {
		for (PlayerProperties character : getAllCharacters()) {
			if(character.getCharacterName().equals(characterName)) {
				return character;
			}
		}
		return null;
	}


	/**
	 * Convert Json to playerProperties
	 * @param rawJson  to convert
	 * @return the playerProperties made from json
	 */
	private ArrayList<PlayerProperties> deserializerPlayerProperties(String rawJson) {
		ArrayList<PlayerProperties> res = new ArrayList<>();
		
		try {
			PlayerProperties[] playerProperties = mapper.readValue(rawJson, PlayerProperties[].class);
			for (PlayerProperties playerProperty : playerProperties) {
				res.add(playerProperty);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Will read string from file
	 * @param filename  of the file to read from
	 * @return the entire text file
	 * @throws IOException
	 */
	private static String readFromFile(String filename) throws IOException {		
		String characterInfo = "";
		File userFile = new File(filename + "characters.json");
		if (userFile.exists()){
			Scanner userFileReader = new Scanner(userFile);

			while(userFileReader.hasNextLine()) {
				String line = userFileReader.nextLine();
				characterInfo += line;
			}
			userFileReader.close();
		}
		else throw new FileNotFoundException("The file could not be found.");
		return characterInfo;
	}

	private String getPath() {
		return this.path;
	}
}
