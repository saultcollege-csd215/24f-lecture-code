package java._06_other_fp_concepts.adts.sum_types_pattern_matching;

public interface NumOrStr {
    record Num(int n) implements NumOrStr {
    }
    record Str(String s) implements NumOrStr {
    }
}
