#!/bin/bash
cat input1.txt | python main.py > output1
echo 'Program Output   |    Correct Output'
diff output1 correctOutput1.txt -y -N -B -W 50

echo '************************************'
cat input2.txt | python main.py > output2
echo 'Program Output   |    Correct Output'
diff output2 correctOutput2.txt -y -N -B -W 50