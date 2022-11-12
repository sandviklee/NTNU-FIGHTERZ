package fightinggame.ui;

import java.util.ArrayList;

import fightinggame.game.PlayerProperties;

public interface CharacterAttributeDAO {
	/**
	 * Gets the PlayerProperties for each Character from data storage.
	 * @return A list of PlayerProperties
	 */
	public ArrayList<PlayerProperties> getAllCharacters();
	/**
	 * Find the spesific PlayerProperties that belongs to the character with characterName as name
	 * @param characterName  of the Charactes PlayerProperties
	 * @return A PlayerProperty
	 */
	public PlayerProperties findCharacter(String characterName);
}
