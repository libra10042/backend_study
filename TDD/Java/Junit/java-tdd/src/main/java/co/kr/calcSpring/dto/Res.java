package co.kr.calcSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Res {
    private int result;
    private Body response;


    public Res(){}
    public Res(int result, Body response) {
        this.result = result;
        this.response = response;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Body getResponse() {
        return response;
    }

    public void setResponse(Body response) {
        this.response = response;
    }

    public static class Body {
        private String resultCode = "OK";

        public Body(){}

        public Body(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }
    }

}
