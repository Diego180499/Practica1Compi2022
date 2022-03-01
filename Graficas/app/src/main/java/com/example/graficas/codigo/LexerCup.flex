package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%column
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type,yyline,yycolumn,value);
    }
    private Symbol symbol(int type){
        return new Symbol(type,yyline,yycolumn);
    }
%}
%%
"," {return new Symbol(sym.Coma, yycolumn, yyline, yytext());}
(Def|def) {return new Symbol(sym.DEF, yycolumn, yyline, yytext());}
(Barras) {return new Symbol(sym.BARRAS, yycolumn, yyline, yytext());}
(Pie) {return new Symbol(sym.PIE, yycolumn, yyline, yytext());}
(titulo) {return new Symbol(sym.Titulo, yycolumn, yyline, yytext());}
(ejex) {return new Symbol(sym.ejeX, yycolumn, yyline, yytext());}
(ejey) {return new Symbol(sym.ejeY, yycolumn, yyline, yytext());}
(etiquetas) {return new Symbol(sym.Etiqueta, yycolumn, yyline, yytext());}
(Ejecutar) {return new Symbol(sym.Ejecutar, yycolumn, yyline, yytext());}
(extra) {return new Symbol(sym.Extra, yycolumn, yyline, yytext());}
(total) {return new Symbol(sym.Total, yycolumn, yyline, yytext());}
(valores) {return new Symbol(sym.Valores, yycolumn, yyline, yytext());}
(unir) {return new Symbol(sym.Unir, yycolumn, yyline, yytext());}
(tipo) {return new Symbol(sym.Tipo, yycolumn, yyline, yytext());}
(Cantidad)  {return new Symbol(sym.Cantidad, yycolumn, yyline, yytext());}
(Porcentaje) {return new Symbol(sym.Porcentaje, yycolumn, yyline, yytext());}
(Definir)   {return new Symbol(sym.DEFINIR, yycolumn, yyline, yytext());}
{espacio} {/*Ignore*/}
"#".* {/*Ignore*/}
"=" {return new Symbol(sym.Igual, yycolumn, yyline, yytext());}
"+" {return new Symbol(sym.Suma, yycolumn, yyline, yytext());}
"-" {return new Symbol(sym.Resta, yycolumn, yyline, yytext());}
"*" {return new Symbol(sym.Multiplicacion, yycolumn, yyline, yytext());}
"/" {return new Symbol(sym.Division, yycolumn, yyline, yytext());}
"(" {return new Symbol(sym.Parentesis_a, yycolumn, yyline, yytext());}
")" {return new Symbol(sym.Parentesis_c, yycolumn, yyline, yytext());}
"" {return new Symbol(sym.Vacio, yycolumn, yyline, yytext());}
"{" {return new Symbol(sym.Llaves_a, yycolumn, yyline, yytext());}
"}" {return new Symbol(sym.Llaves_c, yycolumn, yyline, yytext());}
"[" {return new Symbol(sym.Corchete_a, yycolumn, yyline, yytext());}
"]" {return new Symbol(sym.Corchete_c, yycolumn, yyline, yytext());}
";" {return new Symbol(sym.P_coma, yycolumn, yyline, yytext());}
":" {return new Symbol(sym.Dos_p, yycolumn, yyline, yytext());}
{L}({L}|{D})* {return new Symbol(sym.Identificador, yycolumn, yyline, yytext());}
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yycolumn, yyline, yytext());}
{D}+\.{D}+    {return new Symbol(sym.Decimal, yycolumn, yyline, yytext());}
\"({L}({L}|{D})*)\" {return new Symbol(sym.Nombre, yycolumn, yyline, yytext());}
 . {return new Symbol(sym.ERROR, yycolumn, yyline, yytext());}
