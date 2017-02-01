

	public void testOneThrow() {
		Scoresheet s = new ScoreSheet();
		s.currentFrame - 1;
		s.addThrow(9);
		assertEquals(s.scoreSheet[1].getThrow1(), 9);
		assertEquals(s.addScore(), 9);
	}
	
	public void testTwoThrows() {
		Scoresheet s = new ScoreSheet();
		s.currentFrame = 1;
		s.addThrow(2);
		s.addThrow(4);
		assertEquals(s.scoreSheet[1].getFrameScore(), 6);
		assertEquals(s.addScore(), 6);
	}
	
	public void testThreeThrows(){
		Scoresheet s = new ScoreSheet();
		s.currentFrame = 1;
		s.addThrow(3);
		s.addThrow(5);
		s.currentFrame = 2;
		s.addThrow(6);
		assertEquals(s.scoreSheet[1].getFrameScore(), 8);
		s.addThrow(3);
		assertEquals(s.scoreSheet[2].getFrameScore(), 9);
		s.addThrow(2);
		s.addThrow(3);
		assertEquals(s.addScore(2), 17);
		assertEquals(s.addScore(), 22);
	}
		

	// throw a spare in a frame and make sure its score is correct
	// (counting the following frame, which should also be completed)
	@Test
	public void testSpareCountsNextFrameScore() {
		ScoreSheet s = new ScoreSheet();
		s.scoreSheet[1].setThrow1(7);
		s.scoreSheet[1].setThrow2(3);
		s.scoreSheet[2].setThrow1(3);
		assertEquals(s.addScore(), 16);
	}

	// ensure that a strike frame may not have two throws
	@Test
	public void testStrikeMovesToNextFrame() {
		ScoreSheet s = new ScoreSheet();
		s.currentFrame = 1;
		s.addThrow(10);
		s.addThrow(6);
		assertEquals(s.scoreSheet[1].getFrameScore(), 10);
		assertEquals(s.scoreSheet[2].getThrow1(), 6);
	}

	// throw a strike in a frame and make sure its
	// score is correct (counting the following frames, which should also be
	// completed)
	@Test
	public void testStrikeCountsNextFrameScores() {
		ScoreSheet s = new ScoreSheet();
		s.currentFrame = 1;
		s.addThrow(10);
		s.addThrow(6);
		assertEquals(s.addScore(), 22);
	}

	// test throwing a spare on the 10th frame
	@Test
	public void testSpareOnLastFrame() {
		ScoreSheet s = new ScoreSheet();
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(6);
		s.addThrow(8);
		assertEquals(s.addscore(), 90);
	}

	// Test throwing a strike on the 8th, 9th, and 10th frames
	@Test
	public void testStrikeOnLastFrames() {
		ScoreSheet s = new ScoreSheet();
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(10);
		s.addThrow(6);
		s.addThrow(3);
		assertEquals(s.addscore(), 91);
	}

	// ensure throwing on the 11th frame is not allowed (in some form)
	@Test
	public void testThrowOn11thFrame() {
		ScoreSheet s = new ScoreSheet();
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		s.addThrow(4);
		assertEquals(s.addThrow(9), -1);
	}
}
