# Scuull

A work-in-progress.

I'm re-learning front-end web development from the ground up, and using this
as a project to build something concrete. It's a UI that will display job
history information from the 
[Spring Batch](https://spring.io/projects/spring-batch) metadata tables.

## Running Locally

In the topmost directory, type in:

    docker-compose build
    docker-compse up

Open your browser to [http://localhost:3000](http://localhost:3000).

## Complete

- A Postgress Docker image with the metadata tables and some sample data.
- An API Docker image that serves up the jobs that have run, instances and executions of each of the jobs,
and very basic data from each step of each execution.
- A basic UI to display the information.
- More detailed information about the steps of each execution.
- Error handling.

## In Development

- Security.



