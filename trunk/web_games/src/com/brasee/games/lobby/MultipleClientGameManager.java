package com.brasee.games.lobby;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

public class MultipleClientGameManager {

	private int numberOfGames;
	private Map<Integer, MultipleClientGame> games;
	private GamePreviewImageGeneratorFactory imageGeneratorFactory;
	
	public MultipleClientGameManager(int numberOfGames, GamePreviewImageGeneratorFactory imageGeneratorFactory) {
		if (numberOfGames <= 0) {
			throw new IllegalArgumentException("numberOfGames must be greater than 0");
		}
		if (imageGeneratorFactory == null) {
			throw new IllegalArgumentException("imageGeneratoryFactory must not be null");
		}
		
		this.imageGeneratorFactory = imageGeneratorFactory;
		this.numberOfGames = numberOfGames;
		this.games = new HashMap<Integer, MultipleClientGame>();
		for (int gameId = 1; gameId <= numberOfGames; gameId++) {
			MultipleClientGame game = new MultipleClientGame(this.imageGeneratorFactory.getInstance());
			games.put(gameId, game);
		}
	}
	
	public MultipleClientGame retrieveGame(String gameIdString) {
		MultipleClientGame multipleClientGame = null;
		
		try {
			if (gameIdString != null && StringUtils.isNumeric(gameIdString)) {
				Integer gameId = Integer.parseInt(gameIdString);
				multipleClientGame = this.getGame(gameId);
			}
		}
		catch (Exception e) {
			Logger.getLogger(MultipleClientGameManager.class.getName()).warning("Exception while retrieving game with id " 
					+ gameIdString + ", returning null.");
		}
		
		return multipleClientGame;
	}
	
	public int getNumberOfGames() {
		return numberOfGames;
	}
	
	public MultipleClientGame getGame(int gameId) {
		return games.get(gameId);
	}
	
}
