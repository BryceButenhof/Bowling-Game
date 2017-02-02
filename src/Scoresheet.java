import org.junit.Test;
public class Scoresheet {

	private Frame[] scoreSheet;
	private int currentFrame;
	private int currentThrow;

	public Scoresheet() {
		this.scoreSheet = new Frame[10];
		currentFrame = 0;
		currentThrow = 1;

	}

	public int addThrow(int i) {
		if (i < 0 || i > 10) {
			return -1;
		}

		Frame tmp = scoreSheet[currentFrame];

		if (currentThrow == 1) {
			if (i == 10) {
				tmp.setThrow1(10);
				tmp.setThrow2(0);
				currentThrow = 1;
				currentFrame++;

			} else {
				tmp.setThrow1(i);
				currentThrow = 2;
			}
		} else /* currentThrow == 2 */ {
			if (tmp.getThrow1() + i > 10) {
				return -1;
			} else {
				tmp.setThrow2(i);
				currentThrow = 1;
				currentFrame++;
			}
		}
		return i;

	}

	public int addScore() {
		int Score = 0;

		for (int i = 0; i < 10; i++) {
			Frame tmp = scoreSheet[i];
			if (tmp.getThrow1() + tmp.getThrow2() == 10) {
				int next1 = 0;
				int next2 = 0;

				int k = i + 1;

				if (tmp.getThrow1() == 10) { // Strike
					if (k < 8) {
						if (scoreSheet[k].getThrow1() != 10) { // w/ Anything
																// else
							next1 = scoreSheet[k].getThrow1();
							next2 = scoreSheet[k].getThrow2();
						} else {
							next1 = scoreSheet[k].getThrow1(); // w/ Another
																// Strike
							next2 = scoreSheet[k + 1].getThrow1();
						}
					} else if (k < 9) { // Strike on second
						next1 = scoreSheet[k].getThrow1(); // to last frame
						next2 = scoreSheet[k].getThrow2();
					}
				} else { // Spare
					if (k < 9) {
						next1 = scoreSheet[k].getThrow1();
					}
				}
				Score += 10 + next1 + next2;
			} else { // Normal Thorws
				Score += tmp.getThrow1() + tmp.getThrow2();
			}
		}
		return Score;
	}

	@Test
	public void testOneThrow() {
		Scoresheet s = new Scoresheet();
		s.currentFrame - 1;
		s.addThrow(9);
		assertEquals(s.scoreSheet[1].getThrow1(), 9);
		assertEquals(s.addScore(), 9);
	}

	public void testTwoThrows() {
		Scoresheet s = new Scoresheet();
		s.currentFrame = 1;
		s.addThrow(2);
		s.addThrow(4);
		assertEquals(s.scoreSheet[1].getFrameScore(), 6);
		assertEquals(s.addScore(), 6);
	}

	public void testThreeThrows() {
		Scoresheet s = new Scoresheet();
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
		Scoresheet s = new Scoresheet();
		s.scoreSheet[1].setThrow1(7);
		s.scoreSheet[1].setThrow2(3);
		s.scoreSheet[2].setThrow1(3);
		assertEquals(s.addScore(), 16);
	}

	// ensure that a strike frame may not have two throws
	@Test
	public void testStrikeMovesToNextFrame() {
		Scoresheet s = new Scoresheet();
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
		Scoresheet s = new Scoresheet();
		s.currentFrame = 1;
		s.addThrow(10);
		s.addThrow(6);
		assertEquals(s.addScore(), 22);
	}

	// test throwing a spare on the 10th frame
	@Test
	public void testSpareOnLastFrame() {
		Scoresheet s = new Scoresheet();
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
		Scoresheet s = new Scoresheet();
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
		Scoresheet s = new Scoresheet();
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
