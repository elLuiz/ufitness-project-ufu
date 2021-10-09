package com.ufitness.ufitness.util;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

class SecureTokenGeneratorTest {
    @Test
    void shouldGenerateRandomToken() {
        AssertionsForClassTypes.assertThat(SecureTokenGenerator.generateSecureToken()).isNotBlank();
    }
}