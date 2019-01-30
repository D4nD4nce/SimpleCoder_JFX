package logic.code.helpers;

public class SymbolH
{
    private Character symbol;
    private Integer position;

    public SymbolH(char cSymbol, int nPosition) {
        setSymbol(cSymbol, nPosition);
    }

    public void setSymbol(char symbol, int position) {
        this.symbol = symbol;
        this.position = position;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPosition() {
        return position;
    }
}
