package _04_stratified_design.megamart.stratified_with_abstraction_barrier.businessRules

// NOTE: This layer contains only general business rules, not specific to any particular domain.
// It is a quite "low-level" layer that many other layers can depend on,
// BUT it does not itself depend on any other layers

fun calcTax(total: Double) = total * 0.13