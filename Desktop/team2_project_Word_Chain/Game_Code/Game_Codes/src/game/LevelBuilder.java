
package game;

public interface LevelBuilder {
    int getLevel();
    int getIncorrect();
    void setLevelDifficulty(int level);
    int getScores();
}