/*
 * @(#)Token.java                        2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */


/*
  ITCR- IC-5701 - Proyecto 1
  Modificación realizada
  Token agregados
  * dotdot
  * for
  * from
  * local
  * pipe
  * range
  * recursive
  * repeat
  * select
  * skip
  * to
  * until
  * when

  Token eliminados
  * begin

  Autores:
  Eric Alpizar y Jacob Picado

  Descripción:
  Se agregaron los nuevos tokens compuestos por las palabras reservadas
  y los caracteres nuevos (pipe y dotdot). además se eliminó el token begin.
  Esto causó que se modificara la tabla de tokens.

  Ultima fecha de modificación:
  24/09/2021
*/
package Triangle.SyntacticAnalyzer;


final class Token extends Object {

  protected int kind;
  protected String spelling;
  protected SourcePosition position;

  public Token(int kind, String spelling, SourcePosition position) {

    if (kind == Token.IDENTIFIER) {
      int currentKind = firstReservedWord;
      boolean searching = true;

      while (searching) {
        int comparison = tokenTable[currentKind].compareTo(spelling);
        if (comparison == 0) {
          this.kind = currentKind;
          searching = false;
        } else if (comparison > 0 || currentKind == lastReservedWord) {
          this.kind = Token.IDENTIFIER;
          searching = false;
        } else {
          currentKind ++;
        }
      }
    } else
      this.kind = kind;

    this.spelling = spelling;
    this.position = position;

  }

  public static String spell (int kind) {
    return tokenTable[kind];
  }

  public String toString() {
    return "Kind=" + kind + ", spelling=" + spelling +
      ", position=" + position;
  }

  // Token classes...

  public static final int

    // literals, identifiers, operators...
    INTLITERAL	= 0,
    CHARLITERAL	= 1,
    IDENTIFIER	= 2,
    OPERATOR	= 3,

    // reserved words - must be in alphabetical order...
    ARRAY		= 4,
    CONST		= 5,
    DO			= 6,
    ELSE		= 7,
    END			= 8,
    FOR         = 9,
    FUNC		= 10,
    FROM        = 11,
    IF			= 12,
    IN			= 13,
    LET			= 14,
    LOCAL       = 15,
    OF			= 16,
    PROC		= 17,
    RANGE       = 18,
    RECORD		= 19,
    RECURSIVE   = 20,
    REPEAT      = 21,
    SKIP        = 22,
    THEN		= 23,
    TO          = 24,
    TYPE		= 25,
    UNTIL       = 26,
    VAR			= 27,
    WHEN        = 28,
    WHILE		= 29,

    // punctuation...
    DOT			= 30,
    COLON		= 31,
    SEMICOLON	= 32,
    COMMA		= 33,
    BECOMES		= 34,
    IS			= 35,
    PIPE        = 36,
    DOTDOT      = 37,

    // brackets...
    LPAREN		= 38,
    RPAREN		= 39,
    LBRACKET	= 40,
    RBRACKET	= 41,
    LCURLY		= 42,
    RCURLY		= 43,

    // special tokens...
    EOT			= 44,
    ERROR		= 45;

  private static String[] tokenTable = new String[] {
    "<int>",
    "<char>",
    "<identifier>",
    "<operator>",
    "array",
    "const",
    "do",
    "else",
    "end",
    "for",
    "func",
    "from",
    "if",
    "in",
    "let",
    "local",
    "of",
    "proc",
    "range",
    "record",
    "recursive",
    "repeat",
    "skip",
    "then",
    "to",
    "type",
    "until",
    "var",
    "when",
    "while",
    ".",
    ":",
    ";",
    ",",
    ":=",
    "~",
    "|",
    "..",
    "(",
    ")",
    "[",
    "]",
    "{",
    "}",
    "",
    "<error>"
  };

  private final static int	firstReservedWord = Token.ARRAY,
  				lastReservedWord  = Token.WHILE;

}
