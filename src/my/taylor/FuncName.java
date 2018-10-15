package my.taylor;

public enum FuncName {
    EXP ("Вычисляет экспоненту"), SIN ("Вычисляет синус"), COS("Вычисляет косинус");



    String funcDesc;

    FuncName(String desc){
        this.funcDesc = desc;
    }

    public String getFuncDesc() {
        return funcDesc;
    }
}
