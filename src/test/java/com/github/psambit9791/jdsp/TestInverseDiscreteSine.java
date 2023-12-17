/*
 * Copyright (c) 2019 - 2023  Sambit Paul
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.psambit9791.jdsp;

import com.github.psambit9791.jdsp.transform.InverseDiscreteSine;
import com.github.psambit9791.jdsp.transform._InverseSineCosine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestInverseDiscreteSine {

    // 50Hz Sine + 80Hz Sine sampled @ 100Hz with Nyquist of 50Hz
    private double[] signal1 = {0.0,  0.684,  1.192,  1.401,  1.281,  0.894,  0.373, -0.133, -0.504, -0.688, -0.702,
            -0.615, -0.51, -0.44, -0.409, -0.372, -0.263, -0.033,  0.319,  0.726,  1.074,  1.236,  1.121,  0.714,
            0.093, -0.593, -1.163, -1.464, -1.42 , -1.053, -0.476,  0.147, 0.658,  0.948,  0.991,  0.839,  0.588,
            0.338,  0.154,  0.041, -0.041, -0.154, -0.338, -0.588, -0.839, -0.991, -0.948, -0.658, -0.147,  0.476,
            1.053,  1.42 ,  1.464,  1.163,  0.593, -0.093, -0.714, -1.121, -1.236, -1.074, -0.726, -0.319,  0.033,
            0.263, 0.372,  0.409,  0.44 ,  0.51 ,  0.615,  0.702,  0.688,  0.504, 0.133, -0.373, -0.894, -1.281, -1.401,
            -1.192, -0.684,  0.};

    // 20Hz Sine + 200Hz Sine sampled @ 100Hz with Nyquist of 50Hz
    private double[] signal2 = {0.   ,  0.658,  0.293, -0.04 ,  0.634,  1.212,  0.756,  0.402, 1.035,  1.482,  0.901,
            0.496,  1.062,  1.362,  0.655,  0.208, 0.718,  0.895,  0.1  , -0.346,  0.154,  0.26 , -0.562, -0.943,
            -0.396, -0.302, -1.085, -1.344, -0.703, -0.576, -1.279, -1.384, -0.632, -0.463, -1.08 , -1.04 , -0.2  ,
            -0.017, -0.579, -0.437, 0.437,  0.579,  0.017,  0.2  ,  1.04 ,  1.08 ,  0.463,  0.632, 1.384,  1.279,
            0.576,  0.703,  1.344,  1.085,  0.302,  0.396, 0.943, 0.562, -0.26 , -0.154,  0.346, -0.1  , -0.895, -0.718,
            -0.208, -0.655, -1.362, -1.062, -0.496, -0.901, -1.482, -1.035, -0.402, -0.756, -1.212, -0.634,  0.04 ,
            -0.293, -0.658,  0.};

    @Test
    public void testInverseSineType1Standard() {
        double[] result1 = {0.   ,   1.044,   0.   ,   2.313,  -0.   ,   4.313,   0.   ,
                9.031,  -0.   ,  79.794,   0.   ,  -9.283,   0.   ,  -0.159,
                0.   ,  33.255,   0.   , -13.182,   0.   ,  -7.209,   0.   ,
                -5.259,  -0.   ,  -4.21 ,  -0.   ,  -3.539,  -0.   ,  -3.06 ,
                0.   ,  -2.686,   0.   ,  -2.399,   0.   ,  -2.156,  -0.   ,
                -1.949,  -0.   ,  -1.771,   0.   ,  -1.609,  -0.   ,  -1.472,
                0.   ,  -1.347,   0.   ,  -1.237,  -0.   ,  -1.143,  -0.   ,
                -1.038,  -0.   ,  -0.953,  -0.   ,  -0.859,   0.   ,  -0.794,
                -0.   ,  -0.71 ,  -0.   ,  -0.631,  -0.   ,  -0.577,  -0.   ,
                -0.505,   0.   ,  -0.446,  -0.   ,  -0.377,  -0.   ,  -0.314,
                0.   ,  -0.25 ,   0.   ,  -0.199,   0.   ,  -0.139,  -0.   ,
                -0.084,   0.   ,  -0.021};
        InverseDiscreteSine idct1 = new InverseDiscreteSine(this.signal1);
        idct1.transform(1);
        double[] output1 = idct1.getOutput();
        Assertions.assertArrayEquals(result1, output1, 0.001);

        double[] result2 = {-0.   ,   2.63 ,   0.   ,  79.807,  -0.   ,  -4.867,   0.   ,
                -2.443,  -0.   ,  -1.54 ,  -0.   ,  -1.028,  -0.   ,  -0.659,
                -0.   ,  -0.354,   0.   ,  -0.108,  -0.   ,   0.142,  -0.   ,
                0.382,  -0.   ,   0.646,   0.   ,   0.94 ,  -0.   ,   1.279,
                -0.   ,   1.714,   0.   ,   2.309,  -0.   ,   3.175,  -0.   ,
                4.703,  -0.   ,   8.177,  -0.   ,  25.125,   0.   , -26.387,
                -0.   ,  -8.853,  -0.   ,  -5.353,  -0.   ,  -3.834,   0.   ,
                -2.967,   0.   ,  -2.396,  -0.   ,  -1.981,  -0.   ,  -1.678,
                0.   ,  -1.439,   0.   ,  -1.224,   0.   ,  -1.047,  -0.   ,
                -0.9  ,  -0.   ,  -0.766,  -0.   ,  -0.642,  -0.   ,  -0.537,
                -0.   ,  -0.427,  -0.   ,  -0.335,   0.   ,  -0.228,  -0.   ,
                -0.14 ,   0.   ,  -0.033};
        InverseDiscreteSine idct2 = new InverseDiscreteSine(this.signal2);
        idct2.transform(1);
        double[] output2 = idct2.getOutput();
        Assertions.assertArrayEquals(result2, output2, 0.001);
    }

    @Test
    public void testInverseSineType2Standard() {
        double[] result1 = {-6.369,   7.   ,  -6.172,   8.232,  -6.469,  10.674,  -7.7  ,
                16.793, -12.823,  60.324,  38.573, -20.565,   3.089,  -2.186,
                -1.373,  23.035,  18.069, -19.768,   2.498,  -9.285,   1.218,
                -6.335,   0.701,  -4.842,   0.428,  -3.927,   0.265,  -3.3  ,
                0.16 ,  -2.832,   0.095,  -2.484,   0.051,  -2.201,   0.024,
                -1.97 ,   0.009,  -1.778,   0.001,  -1.61 ,   0.   ,  -1.472,
                0.003,  -1.351,   0.01 ,  -1.247,   0.019,  -1.164,   0.032,
                -1.071,   0.045,  -1.   ,   0.062,  -0.921,   0.075,  -0.871,
                0.096,  -0.807,   0.115,  -0.745,   0.129,  -0.709,   0.153,
                -0.658,   0.172,  -0.621,   0.198,  -0.575,   0.221,  -0.535,
                0.245,  -0.494,   0.265,  -0.465,   0.292,  -0.431,   0.317,
                -0.401,   0.346,  -0.366};
        InverseDiscreteSine idct1 = new InverseDiscreteSine(this.signal1);
        idct1.transform();
        double[] output1 = idct1.getOutput();
        Assertions.assertArrayEquals(result1, output1, 0.001);

        double[] result2 = {-12.971,  15.921, -18.737,  56.945,  43.861, -17.521,   5.609,
                -6.057,   2.151,  -3.128,   1.015,  -1.818,   0.5  ,  -1.061,
                0.234,  -0.549,   0.092,  -0.184,   0.002,   0.135,  -0.045,
                0.414,  -0.073,   0.698,  -0.084,   0.997,  -0.085,   1.334,
                -0.082,   1.76 ,  -0.073,   2.338,  -0.059,   3.183,  -0.045,
                4.681,  -0.029,   8.108,  -0.011,  24.971,  -0.388, -25.922,
                0.032,  -8.769,   0.051,  -5.343,   0.073,  -3.868,   0.096,
                -3.035,   0.121,  -2.495,   0.146,  -2.109,   0.168,  -1.832,
                0.193,  -1.622,   0.223,  -1.438,   0.25 ,  -1.288,   0.276,
                -1.17 ,   0.305,  -1.065,   0.336,  -0.972,   0.364,  -0.899,
                0.401,  -0.824,   0.433,  -0.767,   0.476,  -0.702,   0.512,
                -0.653,   0.562,  -0.591};
        InverseDiscreteSine idct2 = new InverseDiscreteSine(this.signal2);
        idct2.transform();
        double[] output2 = idct2.getOutput();
        Assertions.assertArrayEquals(result2, output2, 0.001);
    }

    @Test
    public void testInverseSineType3Standard() {
        double[] result1 = {0.   ,  0.532,  0.   ,  1.18 ,  0.   ,  2.226,  0.   ,  4.794,
                0.   , 79.927,  0.   , -4.24 ,  0.   ,  0.307,  0.   , 36.943,
                0.   , -6.353,  0.   , -3.719,  0.   , -2.8  ,  0.   , -2.296,
                0.   , -1.977,  0.   , -1.754,  0.   , -1.578,  0.   , -1.451,
                0.   , -1.345,  0.   , -1.257,  0.   , -1.184,  0.   , -1.114,
                0.   , -1.06 ,  0.   , -1.011,  0.   , -0.97 ,  0.   , -0.947,
                0.   , -0.91 ,  0.   , -0.888,  0.   , -0.852,  0.   , -0.844,
                0.   , -0.821,  0.   , -0.793,  0.   , -0.791,  0.   , -0.775,
                0.   , -0.772,  0.   , -0.76 ,  0.   , -0.75 ,  0.   , -0.737,
                0.   , -0.736,  0.   , -0.731,  0.   , -0.732,  0.   , -0.72};
        InverseDiscreteSine idct1 = new InverseDiscreteSine(this.signal1);
        idct1.transform(3);
        double[] output1 = idct1.getOutput();
        Assertions.assertArrayEquals(result1, output1, 0.001);

        double[] result2 = {0.   ,   1.344,   0.   ,  79.484,   0.   ,  -2.364,   0.   ,
                -1.207,   0.   ,  -0.763,   0.   ,  -0.513,   0.   ,  -0.329,
                0.   ,  -0.167,   0.   ,  -0.044,   0.   ,   0.094,   0.   ,
                0.227,   0.   ,   0.384,   0.   ,   0.568,   0.   ,   0.788,
                0.   ,   1.086,   0.   ,   1.519,   0.   ,   2.18 ,   0.   ,
                3.432,   0.   ,   6.667,   0.   ,  35.33 ,   0.   , -12.752,
                0.   ,  -5.77 ,   0.   ,  -3.876,   0.   ,  -2.999,   0.   ,
                -2.491,   0.   ,  -2.159,   0.   ,  -1.916,   0.   ,  -1.746,
                0.   ,  -1.627,   0.   ,  -1.519,   0.   ,  -1.432,   0.   ,
                -1.37 ,   0.   ,  -1.318,   0.   ,  -1.272,   0.   ,  -1.247,
                0.   ,  -1.217,   0.   ,  -1.208,   0.   ,  -1.188,   0.   ,
                -1.188,   0.   ,  -1.164};
        InverseDiscreteSine idct2 = new InverseDiscreteSine(this.signal2);
        idct2.transform(3);
        double[] output2 = idct2.getOutput();
        Assertions.assertArrayEquals(result2, output2, 0.001);
    }

    @Test
    public void testInverseSineType4Standard() {
        double[] result1 = {-6.432,   6.802,  -6.499,   7.72 ,  -7.125,   9.687,  -8.906,
                14.784, -15.638,  51.643,  49.828, -17.159,   4.248,  -1.782,
                -2.022,  18.373,  28.697, -15.463,   4.311,  -7.136,   2.305,
                -4.792,   1.471,  -3.612,   1.011,  -2.894,   0.714,  -2.406,
                0.506,  -2.046,   0.362,  -1.781,   0.246,  -1.569,   0.158,
                -1.398,   0.088,  -1.259,   0.03 ,  -1.138,  -0.012,  -1.041,
                -0.049,  -0.958,  -0.079,  -0.887,  -0.104,  -0.832,  -0.137,
                -0.771,  -0.156,  -0.726,  -0.18 ,  -0.675,  -0.188,  -0.646,
                -0.213,  -0.606,  -0.228,  -0.568,  -0.233,  -0.549,  -0.253,
                -0.52 ,  -0.263,  -0.5  ,  -0.282,  -0.474,  -0.294,  -0.452,
                -0.307,  -0.429,  -0.314,  -0.415,  -0.329,  -0.397,  -0.341,
                -0.383,  -0.357,  -0.362};
        InverseDiscreteSine idct1 = new InverseDiscreteSine(this.signal1);
        idct1.transform(4);
        double[] output1 = idct1.getOutput();
        Assertions.assertArrayEquals(result1, output1, 0.001);

        double[] result2 = {-13.1  ,  15.472, -19.729,  53.404,  48.313, -15.9  ,   6.487,
                -5.332,   2.623,  -2.678,   1.311,  -1.517,   0.688,  -0.865,
                0.345,  -0.438,   0.147,  -0.144,   0.004,   0.104,  -0.084,
                0.314,  -0.153,   0.521,  -0.199,   0.735,  -0.23 ,   0.973,
                -0.259,   1.271,  -0.278,   1.676,  -0.283,   2.269,  -0.296,
                3.323,  -0.294,   5.74 ,  -0.256,  17.658,  27.927, -18.338,
                -0.456,  -6.215,  -0.412,  -3.8  ,  -0.405,  -2.765,  -0.41 ,
                -2.184,  -0.417,  -1.81 ,  -0.424,  -1.545,  -0.423,  -1.358,
                -0.428,  -1.218,  -0.445,  -1.096,  -0.451,  -0.999,  -0.456,
                -0.924,  -0.466,  -0.859,  -0.477,  -0.801,  -0.485,  -0.76 ,
                -0.503,  -0.715,  -0.513,  -0.685,  -0.538,  -0.647,  -0.551,
                -0.624,  -0.58 ,  -0.585};
        InverseDiscreteSine idct2 = new InverseDiscreteSine(this.signal2);
        idct2.transform(4);
        double[] output2 = idct2.getOutput();
        Assertions.assertArrayEquals(result2, output2, 0.001);
    }

    @Test
    public void testInverseSineType1Ortho() {
        double[] result1 = {0.   ,  0.082,  0.   ,  0.182, -0.   ,  0.339,  0.   ,  0.71 ,
                -0.   ,  6.269,  0.   , -0.729,  0.   , -0.013,  0.   ,  2.613,
                0.   , -1.036,  0.   , -0.566,  0.   , -0.413, -0.   , -0.331,
                -0.   , -0.278, -0.   , -0.24 ,  0.   , -0.211,  0.   , -0.189,
                0.   , -0.169, -0.   , -0.153, -0.   , -0.139,  0.   , -0.126,
                -0.   , -0.116,  0.   , -0.106,  0.   , -0.097, -0.   , -0.09 ,
                -0.   , -0.082, -0.   , -0.075, -0.   , -0.067,  0.   , -0.062,
                -0.   , -0.056, -0.   , -0.05 , -0.   , -0.045, -0.   , -0.04 ,
                0.   , -0.035, -0.   , -0.03 , -0.   , -0.025,  0.   , -0.02 ,
                0.   , -0.016,  0.   , -0.011, -0.   , -0.007,  0.   , -0.002};
        InverseDiscreteSine idct1 = new InverseDiscreteSine(this.signal1, _InverseSineCosine.Normalization.ORTHOGONAL);
        idct1.transform(1);
        double[] output1 = idct1.getOutput();
        Assertions.assertArrayEquals(result1, output1, 0.001);

        double[] result2 = {-0.   ,  0.207,  0.   ,  6.27 , -0.   , -0.382,  0.   , -0.192,
                -0.   , -0.121, -0.   , -0.081, -0.   , -0.052, -0.   , -0.028,
                0.   , -0.009, -0.   ,  0.011, -0.   ,  0.03 , -0.   ,  0.051,
                0.   ,  0.074, -0.   ,  0.101, -0.   ,  0.135,  0.   ,  0.181,
                -0.   ,  0.249, -0.   ,  0.369, -0.   ,  0.642, -0.   ,  1.974,
                0.   , -2.073, -0.   , -0.696, -0.   , -0.421, -0.   , -0.301,
                0.   , -0.233,  0.   , -0.188, -0.   , -0.156, -0.   , -0.132,
                0.   , -0.113,  0.   , -0.096,  0.   , -0.082, -0.   , -0.071,
                -0.   , -0.06 , -0.   , -0.05 , -0.   , -0.042, -0.   , -0.034,
                -0.   , -0.026,  0.   , -0.018, -0.   , -0.011,  0.   , -0.003};
        InverseDiscreteSine idct2 = new InverseDiscreteSine(this.signal2, _InverseSineCosine.Normalization.ORTHOGONAL);
        idct2.transform(1);
        double[] output2 = idct2.getOutput();
        Assertions.assertArrayEquals(result2, output2, 0.001);
    }

    @Test
    public void testInverseSineType2Ortho() {
        double[] result1 = {-0.503,  0.553, -0.488,  0.651, -0.511,  0.844, -0.609,  1.328,
                -1.014,  4.769,  3.049, -1.626,  0.244, -0.173, -0.109,  1.821,
                1.428, -1.563,  0.197, -0.734,  0.096, -0.501,  0.055, -0.383,
                0.034, -0.31 ,  0.021, -0.261,  0.013, -0.224,  0.008, -0.196,
                0.004, -0.174,  0.002, -0.156,  0.001, -0.141,  0.   , -0.127,
                0.   , -0.116,  0.   , -0.107,  0.001, -0.099,  0.001, -0.092,
                0.003, -0.085,  0.004, -0.079,  0.005, -0.073,  0.006, -0.069,
                0.008, -0.064,  0.009, -0.059,  0.01 , -0.056,  0.012, -0.052,
                0.014, -0.049,  0.016, -0.045,  0.017, -0.042,  0.019, -0.039,
                0.021, -0.037,  0.023, -0.034,  0.025, -0.032,  0.027, -0.029};
        InverseDiscreteSine idct1 = new InverseDiscreteSine(this.signal1, _InverseSineCosine.Normalization.ORTHOGONAL);
        idct1.transform(2);
        double[] output1 = idct1.getOutput();
        Assertions.assertArrayEquals(result1, output1, 0.001);

        double[] result2 = {-1.025,  1.259, -1.481,  4.502,  3.468, -1.385,  0.443, -0.479,
                0.17 , -0.247,  0.08 , -0.144,  0.04 , -0.084,  0.018, -0.043,
                0.007, -0.015,  0.   ,  0.011, -0.004,  0.033, -0.006,  0.055,
                -0.007,  0.079, -0.007,  0.105, -0.006,  0.139, -0.006,  0.185,
                -0.005,  0.252, -0.004,  0.37 , -0.002,  0.641, -0.001,  1.974,
                -0.031, -2.049,  0.003, -0.693,  0.004, -0.422,  0.006, -0.306,
                0.008, -0.24 ,  0.01 , -0.197,  0.012, -0.167,  0.013, -0.145,
                0.015, -0.128,  0.018, -0.114,  0.02 , -0.102,  0.022, -0.092,
                0.024, -0.084,  0.027, -0.077,  0.029, -0.071,  0.032, -0.065,
                0.034, -0.061,  0.038, -0.055,  0.04 , -0.052,  0.044, -0.047};
        InverseDiscreteSine idct2 = new InverseDiscreteSine(this.signal2, _InverseSineCosine.Normalization.ORTHOGONAL);
        idct2.transform(2);
        double[] output2 = idct2.getOutput();
        Assertions.assertArrayEquals(result2, output2, 0.001);
    }

    @Test
    public void testInverseSineType3Ortho() {
        double[] result1 = {0.   ,  0.042,  0.   ,  0.093,  0.   ,  0.176,  0.   ,  0.379,
                0.   ,  6.319,  0.   , -0.335,  0.   ,  0.024,  0.   ,  2.921,
                0.   , -0.502,  0.   , -0.294,  0.   , -0.221,  0.   , -0.181,
                0.   , -0.156,  0.   , -0.139,  0.   , -0.125,  0.   , -0.115,
                0.   , -0.106,  0.   , -0.099,  0.   , -0.094,  0.   , -0.088,
                0.   , -0.084,  0.   , -0.08 ,  0.   , -0.077,  0.   , -0.075,
                0.   , -0.072,  0.   , -0.07 ,  0.   , -0.067,  0.   , -0.067,
                0.   , -0.065,  0.   , -0.063,  0.   , -0.063,  0.   , -0.061,
                0.   , -0.061,  0.   , -0.06 ,  0.   , -0.059,  0.   , -0.058,
                0.   , -0.058,  0.   , -0.058,  0.   , -0.058,  0.   , -0.057};
        InverseDiscreteSine idct1 = new InverseDiscreteSine(this.signal1, _InverseSineCosine.Normalization.ORTHOGONAL);
        idct1.transform(3);
        double[] output1 = idct1.getOutput();
        Assertions.assertArrayEquals(result1, output1, 0.001);

        double[] result2 = {0.   ,  0.106,  0.   ,  6.284,  0.   , -0.187,  0.   , -0.095,
                0.   , -0.06 ,  0.   , -0.041,  0.   , -0.026,  0.   , -0.013,
                0.   , -0.003,  0.   ,  0.007,  0.   ,  0.018,  0.   ,  0.03 ,
                0.   ,  0.045,  0.   ,  0.062,  0.   ,  0.086,  0.   ,  0.12 ,
                0.   ,  0.172,  0.   ,  0.271,  0.   ,  0.527,  0.   ,  2.793,
                0.   , -1.008,  0.   , -0.456,  0.   , -0.306,  0.   , -0.237,
                0.   , -0.197,  0.   , -0.171,  0.   , -0.151,  0.   , -0.138,
                0.   , -0.129,  0.   , -0.12 ,  0.   , -0.113,  0.   , -0.108,
                0.   , -0.104,  0.   , -0.101,  0.   , -0.099,  0.   , -0.096,
                0.   , -0.096,  0.   , -0.094,  0.   , -0.094,  0.   , -0.092};
        InverseDiscreteSine idct2 = new InverseDiscreteSine(this.signal2, _InverseSineCosine.Normalization.ORTHOGONAL);
        idct2.transform(3);
        double[] output2 = idct2.getOutput();
        Assertions.assertArrayEquals(result2, output2, 0.001);
    }

    @Test
    public void testInverseSineType4Ortho() {
        double[] result1 = {-0.508,  0.538, -0.514,  0.61 , -0.563,  0.766, -0.704,  1.169,
                -1.236,  4.083,  3.939, -1.357,  0.336, -0.141, -0.16 ,  1.453,
                2.269, -1.222,  0.341, -0.564,  0.182, -0.379,  0.116, -0.286,
                0.08 , -0.229,  0.056, -0.19 ,  0.04 , -0.162,  0.029, -0.141,
                0.019, -0.124,  0.012, -0.111,  0.007, -0.1  ,  0.002, -0.09 ,
                -0.001, -0.082, -0.004, -0.076, -0.006, -0.07 , -0.008, -0.066,
                -0.011, -0.061, -0.012, -0.057, -0.014, -0.053, -0.015, -0.051,
                -0.017, -0.048, -0.018, -0.045, -0.018, -0.043, -0.02 , -0.041,
                -0.021, -0.04 , -0.022, -0.038, -0.023, -0.036, -0.024, -0.034,
                -0.025, -0.033, -0.026, -0.031, -0.027, -0.03 , -0.028, -0.029};
        InverseDiscreteSine idct1 = new InverseDiscreteSine(this.signal1, _InverseSineCosine.Normalization.ORTHOGONAL);
        idct1.transform(4);
        double[] output1 = idct1.getOutput();
        Assertions.assertArrayEquals(result1, output1, 0.001);

        double[] result2 = {-1.036,  1.223, -1.56 ,  4.222,  3.819, -1.257,  0.513, -0.422,
                0.207, -0.212,  0.104, -0.12 ,  0.054, -0.068,  0.027, -0.035,
                0.012, -0.011,  0.   ,  0.008, -0.007,  0.025, -0.012,  0.041,
                -0.016,  0.058, -0.018,  0.077, -0.02 ,  0.101, -0.022,  0.133,
                -0.022,  0.179, -0.023,  0.263, -0.023,  0.454, -0.02 ,  1.396,
                2.208, -1.45 , -0.036, -0.491, -0.033, -0.3  , -0.032, -0.219,
                -0.032, -0.173, -0.033, -0.143, -0.034, -0.122, -0.033, -0.107,
                -0.034, -0.096, -0.035, -0.087, -0.036, -0.079, -0.036, -0.073,
                -0.037, -0.068, -0.038, -0.063, -0.038, -0.06 , -0.04 , -0.057,
                -0.041, -0.054, -0.042, -0.051, -0.044, -0.049, -0.046, -0.046};
        InverseDiscreteSine idct2 = new InverseDiscreteSine(this.signal2, _InverseSineCosine.Normalization.ORTHOGONAL);
        idct2.transform(4);
        double[] output2 = idct2.getOutput();
        Assertions.assertArrayEquals(result2, output2, 0.001);
    }
}
