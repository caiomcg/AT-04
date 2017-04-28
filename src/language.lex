%%
%class Lexer
%type Token

DIGIT = [0-9]
IGNORE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{IGNORE} {}

/* Operadores Aritméticos */

( "+" | "-" | "*" | "/" )    {lexeme = yytext(); return Token.OPERATOR;}
("(-"{DIGIT}+")")|{DIGIT}+ {lexeme=yytext(); return Token.INTEGER;}
("(-"{DIGIT}+[.]{DIGIT}+")")|{DIGIT}+[.]{DIGIT}+ {lexeme=yytext(); return Token.FLOAT;}

. {lexeme=yytext(); return Token.ERROR;}