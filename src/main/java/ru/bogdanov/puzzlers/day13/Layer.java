package ru.bogdanov.puzzlers.day13;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Layer {
    private final int range;

    private int counter = 1;

    private boolean reverse = false;
}
