type Type = 
    | TInt
    | TFloat
    | TArray of Type * int option
    | TPointer of Type

type Expr =
    | IntLit of int
    | FloatLit of float
    | BinOp of string * Expr * Expr
    | Var of string
    | Assign of string * Expr
    | Access of string              
    | Addr of string                
    | Cond of Expr * Expr * Expr   
    | Call of string * Expr list   

type Stmt =
    | ExprStmt of Expr
    | Return of Expr
    | If of Expr * Stmt * Stmt    
    | While of Expr * Stmt        
    | Block of Stmt list

type FuncDecl = {
    RetType: Type;
    Name: string;
    Params: (Type * string) list;
    Body: Stmt list;
}

type Program = Prog of FuncDecl list