package com.rules.rules;

public class RuleTuple<L extends IRuleEngine<?, ?>, R> {

    private L left;
    private R right;

    private RuleTuple(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public static <L extends IRuleEngine<?, ?>, R> RuleTuple<L, R> with(
            final L left,
            final R right
    ) {
        return new RuleTuple<>(left, right);
    }

    public L left() {
        return this.left;
    }

    public R right() {
        return this.right;
    }

}
