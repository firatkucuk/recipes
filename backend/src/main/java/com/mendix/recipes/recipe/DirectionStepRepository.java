package com.mendix.recipes.recipe;

import com.mendix.recipes.domain.DirectionStep;
import org.springframework.data.jpa.repository.JpaRepository;

interface DirectionStepRepository extends JpaRepository<DirectionStep, Long> {

}
