package main.com.qub.technopolis.dice;

/**
 * Interface for instances of Dices.
 * <p>
 * Dices can be rolled with {@link #roll()}
 * @author lakrs
 *
 */
public interface Dice {
  /**
   * Roll the dice to get a number back.
   * @return Result of the dice roll, clamped between {@link DiceConfig#getMinRoll()} and {@link DiceConfig#getMaxRoll()}
   */
  public int roll();
}
