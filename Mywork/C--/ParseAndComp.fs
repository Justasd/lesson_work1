open Absyn
open Compile

let compileProgram (prog: Program) : Instruction list = 
    match prog with
    | Prog(funcs) -> 
        List.collect (fun f -> compileFunc f) funcs