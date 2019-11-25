package webEngineering.application.project.taquinv2.utils;

public enum Direction {
	LEFT, RIGTH, UP, DOWN, NONE;

    public boolean isNotInverse(Direction direction) {

        if (this == NONE)
            return true;
        if (this == LEFT && direction != RIGTH)
            return true;
        if (this == RIGTH && direction != LEFT)
            return true;
        if (this == UP && direction != DOWN)
            return true;
        if (this == DOWN && direction != UP)
            return true;
        return  false;
    }

    public Direction getRevese() {

        if (this == LEFT)
            return RIGTH;
        if(this == RIGTH)
            return LEFT;
        if (this == UP)
            return DOWN;
        if(this == DOWN)
            return UP;
        return NONE;
    }
}
