type Instruction = 
    | LoadInt of string * int
    | LoadVar of string * string
    | StoreVar of string * string
    | Add of string * string * string
    | Jump of string
    | CJump of string * string * string
    | Label of string
    | ReturnVal of string

let mutable labelCounter = 0

let resetLabels() = labelCounter <- 0

let newLabel() = 
    labelCounter <- labelCounter + 1
    "L" + string(labelCounter)

let rec compileExpr (expr: Expr) : Instruction list = 
    match expr with
    | Assign(var, e) -> 
        let code = compileExpr e
        code @ [StoreVar(var, "t0")]
    | Var(name) -> [LoadVar("t0", name)]
    | IntLit i -> [LoadInt("t0", i)]
    | BinOp(op, e1, e2) -> 
        let code1 = compileExpr e1
        let code2 = compileExpr e2
        code1 @ code2 @ [Add("t0", "t1", "t2")]
    | Cond(cond, thenE, elseE) ->
        let labelElse = newLabel()
        let labelEnd = newLabel()
        compileExpr cond 
        @ [CJump("t0", labelElse, labelEnd)] 
        @ compileExpr thenE 
        @ [Jump labelEnd] 
        @ [Label labelElse] 
        @ compileExpr elseE 
        @ [Label labelEnd]

let compileStmt (stmt: Stmt) : Instruction list = 
    match stmt with
    | ExprStmt e -> compileExpr e
    | Return e -> 
        compileExpr e @ [ReturnVal "t0"]
    | If(cond, thenS, elseS) ->
        let labelElse = newLabel()
        let labelEnd = newLabel()
        compileExpr cond
        @ [CJump("t0", labelElse, labelEnd)]
        @ compileStmt thenS
        @ [Jump labelEnd]
        @ [Label labelElse]
        @ compileStmt elseS
        @ [Label labelEnd]
    | While(cond, body) ->
        let labelLoop = newLabel()
        let labelEnd = newLabel()
        [Label labelLoop]
        @ compileExpr cond
        @ [CJump("t0", labelEnd, labelLoop)]
        @ compileStmt body
        @ [Jump labelLoop]
        @ [Label labelEnd]
    | Block stmts ->
        List.collect compileStmt stmts

let compileFunc (func: FuncDecl) : Instruction list = 
    let prologue = [Label(func.Name)]
    let bodyCode = List.collect compileStmt func.Body
    prologue @ bodyCode

let compileProgram (prog: Program) : Instruction list = 
    match prog with
    | Prog(funcs) -> 
        List.collect (fun f -> compileFunc f) funcs