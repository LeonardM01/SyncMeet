import React from 'react';

const TextInput = ({ placeholder, value, setValue, className }) => {
    return (
        <input
            type="text"
            placeholder={placeholder}
            value={value}
            onChange={(e) => setValue(e.target.value)}
            className={className}
        />
    );
};
const ProfileEdit = () => {

    const [firstName, setFirstName] = React.useState('');
    const [lastName, setLastName] = React.useState('');
    const [email, setEmail] = React.useState('');
    const [username, setUsername] = React.useState('');

    return (
        <div className={"flex justify-center bg-transparent pt-5 font-nunito"}>
            <div className={"bg-light rounded"}>
                <section className={"flex flex-row justify-between"}>
                    <p className={"text-black heading2 !font-bold content-center"}>Edit profile</p>
                    <img
                        src="/public/profile_picture.png"
                        alt="profile picture"
                        className="w-[100px] h-[100px] rounded-full"
                    />
                </section>
                <form className="flex flex-col gap-y-4">
                <section className={"flex flex-row justify-between mt-4 min-w-full gap-x-10"}>
                    <div className='flex flex-col gap-y-1'>
                        <label className={"text-black heading3-bold"}>First name</label>
                        <TextInput
                            placeholder="Uin"
                            value={firstName}
                            setValue={setFirstName}
                            className="text-black heading3 rounded border border-black ps-5 p-3"
                        />
                        </div>
                        <div className='flex flex-col gap-y-1'>
                        <label className={"text-black heading3-bold"}>Last name</label>
                        <TextInput
                            placeholder="Tglešić"
                            value={lastName}
                            setValue={setLastName}
                            className="text-black heading3 rounded border border-black ps-5 p-3"
                        />
                        </div>
                </section>
                <div className='flex flex-col gap-y-1'>
                    <label className={"text-black heading3-bold"}>Email</label>
                    <TextInput
                        placeholder="uintglesic@gmail.com"
                        value={email}
                        setValue={setEmail}
                        className="text-black heading3 rounded border border-black ps-5 p-3 min-w-full"
                    />
                    </div>
                    <div className='flex flex-col gap-y-1'>
                    <label className={"text-black heading3-bold"}>Username</label>
                    <TextInput
                        placeholder="tinjara"
                        value={username}
                        setValue={setUsername}
                        className="text-black heading3 rounded border border-black ps-5 p-3 min-w-full"
                    />
                    </div>
                </form>
                <section className={"pt-10 flex gap-x-4"}>
                    <button
                        className={"text-orange heading3-bold border-2 h-fit flex-center border-orange rounded bg-white px-8 py-1"}
                    >
                        Cancel
                    </button>
                    <button className={"text-white heading3-bold border-2 border-orange bg-orange rounded px-8 py-1"}>Save</button>
                </section>
            </div>
        </div>
    )
}

export default ProfileEdit;