#include <gtest/gtest.h>

// Przykładowy test
TEST(HelloTest, Test1) {
    EXPECT_EQ(1, 1);
}

int main(int argc, char **argv) {
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
