import { useEffect, useState, useRef } from 'react'
import { api } from '../services/api'
import type { FormEvent } from 'react'
import Navbar from '../components/NavBar'

interface CursoProps {
  id: string
  codigo: string
}

export default function CursosPage() {
  const [cursos, setCursos] = useState<CursoProps[]>([])
  const codigoRef = useRef<HTMLInputElement | null>(null)

  useEffect(() => {
    loadcursos()
  }, [])

  async function loadcursos() {
    try {
      const response = await api.get("/cursos")
      setCursos(response.data)
    } catch (error) {
      console.error("Erro ao carregar cursos:", error)
    }
  }

async function handleSubmit(event: FormEvent) {
  event.preventDefault()
  if (!codigoRef.current?.value) return

  try {
    const response = await api.post("/cursos", {
      codigo: codigoRef.current.value
    })
    setCursos(prev => [...prev, response.data])
    codigoRef.current.value = ''
    alert("Curso cadastrado com sucesso")
  } catch (error: any) {
    if (error.response && error.response.status === 409) {
      alert(error.response.data)
    } else {
      console.error("Erro ao cadastrar curso:", error)
    }
  }
}

  return (
    <div className="w-full min-h-screen bg-gray-900 flex flex-col">
      <Navbar />

      <main className="mt-24 my-10 w-full md:max-w-2xl mx-auto px-4">
        <h1 className="text-4xl font-medium text-white mb-6">Cursos</h1>

        <form className="flex flex-col my-6" onSubmit={handleSubmit}>
          <label className="font-medium text-white mb-2">Código:</label>
          <input
            type="text"
            placeholder="Digite o código do Curso:"
            className="w-full mb-5 p-2 rounded text-white bg-gray-800 placeholder-gray-400"
            ref={codigoRef}
          />

          <input
            type="submit"
            value="Cadastrar"
            className="cursor-pointer w-full p-2 bg-green-500 rounded font-medium hover:bg-green-600 duration-200"
          />
        </form>

        <section className="flex flex-col gap-4">
          {cursos.map(curso => (
            <div
              key={curso.id}
              className="w-full bg-white rounded p-2 relative hover:scale-105 duration-200"
            >
              <p><span className="font-medium">Código:</span> {curso.codigo}</p>
              <p><span className="font-medium">Id:</span> {curso.id}</p>

            </div>
          ))}
        </section>
      </main>
    </div>
  )
}
