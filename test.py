while True:
    user_input = input("What operation do you want to do? ")
    if user_input.lower() == 'multiplication':
        num1 = int(input('Type the first number you want to multiply: '))
        num2 = int(input('Type the second number you want to multiply: '))
        answer = num1 * num2
        print('The answer is ' + str(answer))
    elif user_input.lower() == 'addition':
        num1 = int(input('Type the first number you want to add: '))
        num2 = int(input('Type the second number you want to add: '))
        answer = num1 + num2
        print('The answer is ' + str(answer))
    elif user_input.lower() == 'division':
        num1 = int(input('Type the number you want to divide: '))
        num2 = int(input('Type the number you want to divide by: '))
        answer = num1 / num2
        print('The answer is ' + str(answer))
    elif user_input.lower() == 'subtraction':
        num1 = int(input('Type the number you want to subtract from: '))
        num2 = int(input('Type the number you want to subtract: '))
        answer = num1 - num2
        print('The answer is ' + str(answer))
    else:
        print('Check your typing')
    again = input('Do another calculation? (y/n)? ')
    if again.lower() != 'y':
        break
