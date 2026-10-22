use rand::Rng;
use std::io;

fn read_guess() -> Option<i32> {
    let mut input = String::new();
    io::stdin()
        .read_line(&mut input)
        .expect("Failed to read line");
    input.trim().parse().ok()
}
fn main(){
    let mut rng = rand::rng();
    let answer: i32 = rng.random_range(1..=100);
    println!("Guess a number from 1-100:");

    loop {
        let guess = match read_guess(){
            Some(num) => num,
            None => {
                println!("Invalid Number");
                continue;
            }
        };

        if guess > answer {
            println!("You guessed too high!");
        }   else if guess < answer {
            println!("You guessed too low!");
        }   else {
            println!("You guessed it! The answer was {}", answer);
            break;
        }   
        println!("Try again:");
    }
}
