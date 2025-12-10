import { useEffect, useState, useRef } from 'react'
import { api } from '../services/api'
import type { FormEvent } from 'react'
import Navbar from '../components/NavBar'

interface AlunoProps {
  id: string
  matricula: string
}

export default function AlunosPage() {

  const [alunos, setAlunos] = useState<AlunoProps[]>([])
  const matriculaRef = useRef<HTMLInputElement | null>(null)

  useEffect(() => {
    loadAlunos()
  }, [])

  async function loadAlunos() {
    try {
      const response = await api.get("/alunos")
      setAlunos(response.data)
    } catch (error) {
      console.error("Erro ao carregar alunos:", error)
    }
  }

async function handleSubmit(event: FormEvent) {
  event.preventDefault()
  if (!matriculaRef.current?.value) return

  try {
    const response = await api.post("/alunos", {
      matricula: matriculaRef.current.value
    })
    setAlunos(prev => [...prev, response.data])
    matriculaRef.current.value = ''
    alert("Aluno cadastrado com sucesso")
  } catch (error: any) {
    if (error.response && error.response.status === 409) {
      alert(error.response.data)
    } else {
      console.error("Erro ao cadastrar aluno:", error)
    }
  }
}

  return (
    <div className="w-full min-h-screen bg-gray-900 flex flex-col">
      <Navbar />
      <main className="mt-24 my-10 w-full md:max-w-2xl mx-auto px-4">
        <h1 className="text-4xl font-medium text-white">Alunos</h1>

        <form className="flex flex-col my-6" onSubmit={handleSubmit}>
          <label className="font-medium text-white">Matrícula:</label>
          <input
            type="text"
            placeholder="Digite a matrícula do aluno:"
            className="w-full mb-5 p-2 rounded text-white bg-gray-800 placeholder-gray-400"
            ref={matriculaRef}
          />

          <input
            type="submit"
            value="Cadastrar"
            className="cursor-pointer w-full p-2 bg-green-500 rounded font-medium hover:bg-green-600 duration-200"
          />
        </form>

        <section className="flex flex-col gap-4">
          {alunos.map(aluno => (
            <div
              key={aluno.id}
              className="w-full bg-white rounded p-2 relative hover:scale-105 duration-200"
            >
              <div>
                <p><span className="font-medium">Matrícula:</span> {aluno.matricula}</p>
                <p><span className="font-medium">Id:</span> {aluno.id}</p>

              </div>
            </div>
          ))}
        </section>
      </main>
    </div>
  )
}