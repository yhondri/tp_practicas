package tp.pr3.inst.conditionals;

import tp.pr3.bc.ByteCode;
import tp.pr3.bc.jumps.ConditionalJump;
import tp.pr3.bc.jumps.conditionaljumps.IfLeq;
import tp.pr3.elements.Compiler;
import tp.pr3.elements.LexicalParser;
import tp.pr3.exceptions.ArrayException;
import tp.pr3.inst.assigments.Term;
import tp.pr3.inst.assigments.TermParser;

public class LessEq extends Condition {

    public LessEq(){}

    public LessEq(Term term1, Term term2) {
        super(term1, term2);
        this.conditionalJump = new IfLeq();
    }

    @Override
    public Condition parse(String t1, String op, String t2, LexicalParser parser) {
        if (op.equals("<=")) {
            Term term1 = TermParser.parse(t1);
            Term term2 = TermParser.parse(t2);
            if (term1 != null && term2 != null) {
                //TODO: Check this
                parser.increaseProgramCounter();
                return new LessEq(term1, term2);
            }
        }
        return null;
    }

    @Override
    public void compile(Compiler compiler) throws ArrayException {
        compiler.addByteCode(new IfLeq());
        ByteCode term1ByteCode = this.term1.compile(compiler);
        compiler.addByteCode(term1ByteCode);
        ByteCode term2ByteCode = this.term2.compile(compiler);
        compiler.addByteCode(term2ByteCode);
        compiler.addByteCode(this.conditionalJump);
    }

    @Override
    protected ConditionalJump compileOp() {
        return null;
    }

    @Override
    public boolean compare(int par1, int par2) {
        return !(par2 <= par1);
    }

}
