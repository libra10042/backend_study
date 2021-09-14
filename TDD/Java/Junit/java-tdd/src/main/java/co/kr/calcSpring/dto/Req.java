package co.kr.calcSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Req {
    private int x1;
    private int y2;

    public Req(int x1, int y2) {
        this.x1 = x1;
        this.y2 = y2;
    }

    public Req(){}
    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
