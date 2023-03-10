package com.example.mydietolog.data;

public class CreatorQuery {
    private String query = "";

    public CreatorQuery createTable(){
        addQuery("CREATE TABLE");
        return this;
    }

    public CreatorQuery dropTable(){
        addQuery("DROP TABLE");
        return this;
    }

    public CreatorQuery ifExists(){
        addQuery("IF EXISTS");
        return this;
    }

    public CreatorQuery ifNotExists(){
        addQuery("IF NOT EXISTS");
        return this;
    }

    public CreatorQuery addName(String value){
        value = value.trim();
        if(!value.contains(" ")){
            addQuery(value);
        }
        return this;
    }

    public CreatorQuery open(){
        addQuery("(");
        return this;
    }

    public CreatorQuery close(){
        addQuery(";");
        return this;
    }

    public CreatorQuery closeBracket(){
        addQuery(")");
        return this;
    }

    public CreatorQuery closePunkt(){
        addQuery(",");
        return this;
    }

    public CreatorQuery integer(){
        addQuery("INTEGER");
        return this;
    }

    public CreatorQuery text(){
        addQuery("TEXT");
        return this;
    }

    public CreatorQuery real(){
        addQuery("REAL");
        return this;
    }

    public CreatorQuery blob(){
        addQuery("BLOB");
        return this;
    }

    public CreatorQuery unique(){
        addQuery("UNIQUE");
        return this;
    }

    public CreatorQuery notnull(){
        addQuery("NOT NULL");
        return this;
    }

    public CreatorQuery select(){
        addQuery("SELECT");
        return this;
    }

    public CreatorQuery all(){
        addQuery("*");
        return this;
    }

    public CreatorQuery from(String value){
        addQuery("FROM " + value);
        return this;
    }

    public CreatorQuery where(String condition){
        addQuery("WHERE " + condition);
        return this;
    }

    public CreatorQuery groupby(String column){
        addQuery("GROUP BY " + column);
        return this;
    }

    public CreatorQuery insert(){
        addQuery("INSERT INTO");
        return this;
    }

    public CreatorQuery having(String condition){
        addQuery("HAVING " + condition);
        return this;
    }

    public CreatorQuery addColumn(String value){
        addName(value);
        closePunkt();

        return this;
    }

    public CreatorQuery insert(String table, String[] columns){
        insert().addName(table).open();

        for(String column: columns){
            addColumn(column);
        }
        closeBracket().addName("VALUES");

        return this;
    }
    public String get(){
        return query;
    }

    private void addQuery(String value){
        query += value + " ";
    }
}
